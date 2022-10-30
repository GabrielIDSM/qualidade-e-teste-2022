/*
 * Util
 */

function ToNumber(n) {
    return isNaN(Number(n)) ? Number("0") : Number(n);
}

/*
 * I18N
 */

function i18n(id, I18n, language) {
    let w = id;
    I18n.forEach(function(value) {
        if (value["id"] === id) {
            if (language === 1) w = value["pt_BR"];
            else if (language === 2) w = value["en_US"];
        }
    });
    return w;
}

/*
 * Header
 */

function setDownload(pcap, id) {
    $(id).attr("href", "PCAP/" + pcap);
}

function titleState(id, icon, line) {
    let state;
    state = $(id).attr("state");
    if (state === "visible") {
        $(id).attr("state", "hidden");
        $(id).slideUp(500);
        $(icon).css("transition-duration", "500ms");
        $(icon).css("transition-property", "transform");
        $(icon).css("transform", "rotate(-90deg)");
        $(line).css("transition-duration", "500ms");
        $(line).css("transition-property", "margin-bottom");
        $(line).css("margin-bottom", "1rem");
    } else {
        $(id).attr("state", "visible");
        $(id).slideDown(500);
        $(icon).css("transition-duration", "500ms");
        $(icon).css("transition-property", "transform");
        $(icon).css("transform", "rotate(0deg)");
        $(line).css("transition-duration", "500ms");
        $(line).css("transition-property", "margin-bottom");
        $(line).css("margin-bottom", "0");
    }
}

function filterState(title, content, color, icon) {
    let state;
    state = $(content).attr("state");
    if (state === "visible") {
        $(content).attr("state", "hidden");
        $(content).slideUp(500);
        $(title).css("background-color", color);
        $(icon).css("transition-duration", "500ms");
        $(icon).css("transition-property", "transform");
        $(icon).css("transform", "rotate(-90deg)");
    } else {
        $(content).attr("state", "visible");
        $(content).slideDown(500);
        $(title).css("background-color", "#FBFBF8");
        $(icon).css("transition-duration", "500ms");
        $(icon).css("transition-property", "transform");
        $(icon).css("transform", "rotate(0deg)");
    }
}

function downloadState(id) {
    let state;
    state = $(id).attr("state");
    if (state === "visible") {
        $(id).attr("state", "hidden");
        $(id).slideUp(400);
    } else {
        $(id).attr("state", "visible");
        $(id).slideDown(400);
    }
}

function infoState(id) {
    let state;
    state = $(id).attr("state");
    if (state === "visible") {
        $(id).attr("state", "hidden");
        $(id).slideUp(400);
    } else {
        $(id).attr("state", "visible");
        $(id).slideDown(400);
    }
}

function showNewFilter() {
    $("#new-filter").hide(0);
    $("#dashboard-new-filter").show(0);
    $(".dashboard-view").css("opacity", "0.5");
    $(".dashboard-view").css("pointer-events", "none");
}

function hideNewFilter() {
    $("#new-filter").show(0);
    $("#dashboard-new-filter").hide(0);
    $(".dashboard-view").css("opacity", "1");
    $(".dashboard-view").css("pointer-events", "auto");
    $(".selectpicker").selectpicker("deselectAll");
}

$(function () {
    $("#dashboard-help-button").hover(function () {
        $("#dashboard-help-content").show();
    },
    function () {
        $("#dashboard-help-content").hide();
    });
});

/*
 * Chart
 */

function drawChartInternal(id, labels, datasets, I18n, l) {
    new Chart(id, {
        type: "line",
        data: {
            labels: labels,
            datasets: datasets
        },
        options: {
            interaction: {
                intersect: false,
                mode: "index"
            },
            plugins: {
                legend: {
                    labels: {
                        usePointStyle: true,
                        pointStyleWidth: 5,
                        pointStyle: "circle",
                    },
                    position: "bottom",
                    align: "center",
                    display: true
                }
            },
            scales: {
                x: {
                    ticks: {
                        source: "auto",
                        maxRotation: 0,
                        autoSkip: true
                    },
                    title: {
                        display: true,
                        text: i18n("POSITIONS", I18n, l)
                    }
                },
                y: {
                    beginAtZero: false,
                    stacked: false
                }
            },
            responsive: true,
            fill: true,
            maintainAspectRatio: false
        }
    });
}

function drawChart(id, chartType, data, data2, hiddenEmpty, I18n, l) {
    let bpm = null, bpmWristwatch = null, respiratoryRate = null;
    switch(chartType) {
        case "BPM":
            bpm = data;
            bpmWristwatch = data2;
            break;
        case "Respiratory Rate":
            respiratoryRate = data;
            break;
    }

    let datasets = [], labels = [], usedLabels = [];
    for (let i = 0; i <= 17; i++) {
        labels.push(i);
        usedLabels.push(false);
    }

    let bpmDataValues = [];
    if (bpm !== null) {
        for (let i = 0; i <= 17; i++) bpmDataValues.push(null);

        bpm.forEach(function (o) {
            if (o["bpm"] !== null && !isNaN(Number(o["bpm"]))) {
                bpmDataValues[o["code"]] = ToNumber(o["bpm"]);
                usedLabels[o["code"]] = true;
            }
        });
    }

    let bpmWristwatchDataValues = [];
    if (bpmWristwatch !== null) {
        for (let i = 0; i <= 17; i++) bpmWristwatchDataValues.push(null);

        bpmWristwatch.forEach(function (o) {
            if (o["bpm"] !== null && !isNaN(Number(o["bpm"]))) {
                bpmWristwatchDataValues[o["code"]] = ToNumber(o["bpm"]);
                usedLabels[o["code"]] = true;
            }
        });
    }

    let respiratoryRateDataValues = [];
    if (respiratoryRate !== null) {
        for (let i = 0; i <= 17; i++) respiratoryRateDataValues.push(null);

        respiratoryRate.forEach(function (o) {
            if (o["respiratoryRate"] !== null && !isNaN(Number(o["respiratoryRate"]))) {
                respiratoryRateDataValues[o["code"]] = ToNumber(o["respiratoryRate"]);
                usedLabels[o["code"]] = true;
            }
        });
    }
    
    if (hiddenEmpty) {
        let rmCount = 0;
        for (let i = 0; i <= 17; i++) {
            if (!usedLabels[i]) {
                labels.splice(i - rmCount, 1);
                if (bpm !== null)
                    bpmDataValues.splice(i - rmCount, 1);
                if (bpmWristwatch !== null)
                    bpmWristwatchDataValues.splice(i - rmCount, 1);
                if (respiratoryRate !== null)
                    respiratoryRateDataValues.splice(i - rmCount, 1);
                rmCount++;
            }
        }
    }

    if (bpm !== null) {
        datasets.push({
            label: i18n("BPM", I18n, l),
            data: bpmDataValues,
            borderColor: "rgba(170, 20, 20, 1)",
            backgroundColor: "rgba(170, 20, 20, 1)",
            tension: 0.09,
            pointRadius: 5,
            showLine: false,
            fill: false
        });
    }

    if (bpmWristwatch !== null) {
        datasets.push({
            label: i18n("WRISTWATCH_BPM", I18n, l),
            data: bpmWristwatchDataValues,
            borderColor: "rgba(170, 20, 20, 1)",
            backgroundColor: "rgba(170, 20, 20, 0.25)",
            tension: 0.09,
            pointRadius: 5,
            showLine: false,
            fill: false
        });
    }

    if (respiratoryRate !== null) {
        datasets.push({
            label: i18n("RESPIRATORY_RATE", I18n, l),
            data: respiratoryRateDataValues,
            borderColor: "rgba(20, 170, 170, 1)",
            backgroundColor: "rgba(20, 170, 170, 1)",
            tension: 0.09,
            pointRadius: 5,
            showLine: false,
            fill: false
        });
    }

    drawChartInternal(id, labels, datasets, I18n, l);
}

function drawAggrChart(id, chartType, data, hiddenEmpty, I18n, l) {
    let labels = [], datasets = [], usedLabels = [];
    let bpmDataValues = [], bpmWristwatchDataValues = [], respiratoryRateDataValues = [];
    let bpmDataCount = 0, bpmWristwatchDataCount = 0, respiratoryRateDataCount = 0;
    
    for (let i = 0; i <= 17; i++) {
        labels.push(i);
        usedLabels.push(false);
        bpmDataValues.push(0);
        bpmWristwatchDataValues.push(0);
        respiratoryRateDataValues.push(0);
    }

    for (let i = 0; i < data.length; i++) {
        switch(chartType) {
            case "BPM":
                for (let j = 0; j < data[i]["bpm"].length; j++) {
                    let o = data[i]["bpm"][j];
                    if (o["bpm"] !== null && !isNaN(Number(o["bpm"]))) {
                        bpmDataValues[o["code"]] = bpmDataValues[o["code"]] + ToNumber(o["bpm"]);
                        usedLabels[o["code"]] = true;
                    }
                }
                for (let j = 0; j < data[i]["wristwatchBpm"].length; j++) {
                    let o = data[i]["wristwatchBpm"][j];
                    if (o["bpm"] !== null && !isNaN(Number(o["bpm"]))) {
                        bpmWristwatchDataValues[o["code"]] = bpmWristwatchDataValues[o["code"]] + ToNumber(o["bpm"]);
                        usedLabels[o["code"]] = true;
                    }
                }
                bpmDataCount++;
                bpmWristwatchDataCount++;
                break;
            case "Respiratory Rate":
                for (let j = 0; j < data[i]["respiratoryRate"].length; j++) {
                    let o = data[i]["respiratoryRate"][j];
                    if (o["respiratoryRate"] !== null && !isNaN(Number(o["respiratoryRate"]))) {
                        respiratoryRateDataValues[o["code"]] = respiratoryRateDataValues[o["code"]] + ToNumber(o["respiratoryRate"]);
                        usedLabels[o["code"]] = true;
                    }
                }
                respiratoryRateDataCount++;
                break;
        }
    }
    
    if (hiddenEmpty) {
        let rmCount = 0;
        for (let i = 0; i <= 17; i++) {
            if (!usedLabels[i]) {
                labels.splice(i - rmCount, 1);
                if (bpmDataCount > 0)
                    bpmDataValues.splice(i - rmCount, 1);
                if (bpmWristwatchDataCount > 0)
                    bpmWristwatchDataValues.splice(i - rmCount, 1);
                if (respiratoryRateDataCount > 0)
                    respiratoryRateDataValues.splice(i - rmCount, 1);
                rmCount++;
            }
        }
    }
    
    let fBpmDataValues = [], fBpmWristwatchDataValues = [], fRespiratoryRateDataValues = [];
    if (bpmDataCount > 0) {
        for (let i = 0; i < bpmDataValues.length; i++) {
            fBpmDataValues.push(bpmDataValues[i] / bpmDataCount);
        }
        
        datasets.push({
            label: i18n("BPM_AVG", I18n, l),
            data: fBpmDataValues,
            borderColor: "rgba(170, 20, 20, 1)",
            backgroundColor: "rgba(170, 20, 20, 1)",
            tension: 0.09,
            pointRadius: 5,
            showLine: false,
            fill: false
        });
    }
    
    if (bpmWristwatchDataCount > 0) {
        for (let i = 0; i < bpmDataValues.length; i++) {
            fBpmWristwatchDataValues.push(bpmWristwatchDataValues[i] / bpmWristwatchDataCount);
        }
        
        datasets.push({
            label: i18n("WRISTWATCH_BPM_AVG", I18n, l),
            data: fBpmWristwatchDataValues,
            borderColor: "rgba(170, 20, 20, 1)",
            backgroundColor: "rgba(170, 20, 20, 0.25)",
            tension: 0.09,
            pointRadius: 5,
            showLine: false,
            fill: false
        });
    }
    
    if (respiratoryRateDataCount > 0) {
        for (let i = 0; i < bpmDataValues.length; i++) {
            fRespiratoryRateDataValues.push(respiratoryRateDataValues[i] / respiratoryRateDataCount);
        }
        
        datasets.push({
            label: i18n("RESPIRATORY_RATE_AVG", I18n, l),
            data: fRespiratoryRateDataValues,
            borderColor: "rgba(20, 170, 170, 1)",
            backgroundColor: "rgba(20, 170, 170, 1)",
            pointRadius: 5,
            showLine: false,
            tension: 0.09,
            fill: false
        });
    }

    drawChartInternal(id, labels, datasets, I18n, l);
}

function drawGpChart(id, chartType, data, hiddenEmpty, I18n, l) {
    let labels = [], datasets = [], usedLabels = [];

    for (let i = 0; i <= 17; i++) {
        labels.push(i);
        usedLabels.push(false);
    }
    
    let bpmDataValues = [], bpmWristwatchDataValues = [], respiratoryRateDataValues = [];
    for (let i = 0; i < data.length; i++) {
        let bpm = null, bpmWristwatch = null, respiratoryRate = null;
        switch(chartType) {
            case "BPM":
                bpm = data[i]["bpm"];
                bpmWristwatch = data[i]["wristwatchBpm"];
                bpmDataValues[i] = [];
                bpmWristwatchDataValues[i] = [];
                break;
            case "Respiratory Rate":
                respiratoryRate = data[i]["respiratoryRate"];
                respiratoryRateDataValues[i] = [];
                break;
        }
        
        if (bpm !== null) {
            for (let j = 0; j <= 17; j++) bpmDataValues[i].push(null);
            for (let j = 0; j < bpm.length; j++) {
                if (bpm[j] !== null && !isNaN(Number(bpm[j]["bpm"]))) {
                    bpmDataValues[i][bpm[j]["code"]] = ToNumber(bpm[j]["bpm"]);
                    usedLabels[bpm[j]["code"]] = true;
                }
            }
        }

        if (bpmWristwatch !== null) {
            for (let j = 0; j <= 17; j++) bpmWristwatchDataValues[i].push(null);
            for (let j = 0; j < bpmWristwatch.length; j++) {
                if (bpmWristwatch[j] !== null && !isNaN(Number(bpmWristwatch[j]["bpm"]))) {
                    bpmWristwatchDataValues[i][bpmWristwatch[j]["code"]] = ToNumber(bpmWristwatch[j]["bpm"]);
                    usedLabels[bpmWristwatch[j]["code"]] = true;
                }
            }
        }

        if (respiratoryRate !== null) {
            for (let j = 0; j <= 17; j++) respiratoryRateDataValues[i].push(null);
            for (let j = 0; j < respiratoryRate.length; j++) {
                if (respiratoryRate[j] !== null && !isNaN(Number(respiratoryRate[j]["respiratoryRate"]))) {
                    respiratoryRateDataValues[i][respiratoryRate[j]["code"]] = ToNumber(respiratoryRate[j]["respiratoryRate"]);
                    usedLabels[respiratoryRate[j]["code"]] = true;
                }
            }
        }
    }
    
    if (hiddenEmpty) {
        let rmCount = 0;
        for (let i = 0; i <= 17; i++) {
            if (!usedLabels[i]) {
                labels.splice(i - rmCount, 1);
                if (bpmDataValues.length > 0) {
                    for (let j = 0; j < bpmDataValues.length; j++) {
                        bpmDataValues[j].splice(i - rmCount, 1);
                    }
                }
                if (bpmWristwatchDataValues.length > 0) {
                    for (let j = 0; j < bpmWristwatchDataValues.length; j++) {
                        bpmWristwatchDataValues[j].splice(i - rmCount, 1);
                    }
                }
                if (respiratoryRateDataValues.length > 0) {
                    for (let j = 0; j < respiratoryRateDataValues.length; j++) {
                        respiratoryRateDataValues[j].splice(i - rmCount, 1);
                    }
                }
                rmCount++;
            }
        }
    }
    
    for (let i = 0; i < data.length; i++) {
        let colors = [
            "rgba(0, 127, 127",     "rgba(127, 0, 127",     "rgba(127, 127, 0",
            "rgba(0, 127, 255",     "rgba(127, 0, 255",     "rgba(127, 255, 0",
            "rgba(0, 255, 127",     "rgba(255, 0, 127",     "rgba(255, 127, 0",
            "rgba(127, 127, 255",   "rgba(127, 127, 255",   "rgba(127, 255, 127",
            "rgba(127, 255, 127",   "rgba(255, 127, 127",   "rgba(255, 127, 127",
            "rgba(255, 127, 255",   "rgba(127, 255, 255",   "rgba(127, 255, 255",
            "rgba(255, 255, 127",   "rgba(255, 255, 127",   "rgba(255, 127, 255",
            "rgba(0, 255, 255",     "rgba(255, 0, 255",     "rgba(255, 255, 0"
        ];

        switch(chartType) {
            case "BPM":
                datasets.push({
                    label: data[i]["id"] + " (" + i18n("BPM", I18n, l) + ")",
                    data: bpmDataValues[i],
                    borderColor: colors[i % colors.length] + ", 1)",
                    backgroundColor: colors[i % colors.length] + ", 1)",
                    tension: 0.09,
                    pointRadius: 5,
                    showLine: false,
                    fill: false
                });
                datasets.push({
                    label: data[i]["id"] + " (" + i18n("WRISTWATCH_BPM", I18n, l) + ")",
                    data: bpmWristwatchDataValues[i],
                    borderColor: colors[i % colors.length] + ", 1)",
                    backgroundColor: colors[i % colors.length] + ", 0.25)",
                    tension: 0.09,
                    pointRadius: 5,
                    showLine: false,
                    fill: false
                });
                break;
            case "Respiratory Rate":
                datasets.push({
                    label: data[i]["id"] + " (" + i18n("RESPIRATORY_RATE", I18n, l) + ")",
                    data: respiratoryRateDataValues[i],
                    borderColor: colors[i % colors.length] + ", 1)",
                    backgroundColor: colors[i % colors.length] + ", 1)",
                    tension: 0.09,
                    pointRadius: 5,
                    showLine: false,
                    fill: false
                });
                break;
        }
    }
    
    drawChartInternal(id, labels, datasets, I18n, l);
}

function downloadTableAsCSV(tableId, separator = ',') {
    var rows = document.querySelectorAll('table#' + tableId + ' tr');
    var csv = [];
    for (var i = 0; i < rows.length; i++) {
        var row = [], cols = rows[i].querySelectorAll('td, th');
        for (var j = 0; j < cols.length; j++) {
            var data = cols[j].innerText.replace(/(\r\n|\n|\r)/gm, '').replace(/(\s\s)/gm, ' ');
            data = data.replace(/"/g, '""');
            row.push('"' + data + '"');
        }
        csv.push(row.join(separator));
    }
    var csv_string = csv.join('\n');
    var filename = 'export_table_' + new Date().toLocaleDateString() + '.csv';
    var link = document.createElement('a');
    link.style.display = 'none';
    link.setAttribute('target', '_blank');
    link.setAttribute('href', 'data:text/csv;charset=utf-8,' + encodeURIComponent(csv_string));
    link.setAttribute('download', filename);
    document.body.appendChild(link);
    link.click();
    document.body.removeChild(link);
}