function i18n(id, I18n, language) {
    var w = id;
    I18n.forEach(function(value) {
        if (value["id"] === id) {
            if (language === 1) w = value["pt_BR"];
            else if (language === 2) w = value["en_US"];
        }
    });
    return w;
}

function drawChartInternal(id, labels, datasets, hiddenEmpty) {
    var fLabels = [], fDatasets = [], aux = [], i;
    
    if (hiddenEmpty) {
        for (k = 0; k < datasets.length; k++) {
            aux[k] = [];
        }
        for (i = 0; i < labels.length; i++) {
            var hasValue = false;
            datasets.forEach(function(value) {
                var data = value["data"];
                if (data !== null) {
                    if (data[i] !== null) {
                        hasValue = true;
                    }
                }
            });
            if (hasValue) {
                fLabels.push(labels[i]);
                for (j = 0; j < aux.length; j++) {
                    for (k = 0; k < datasets.length; k++) {
                        aux[j].push(datasets[k]["data"][i]);
                    }
                }
            }
        }
        for (k = 0; k < datasets.length; k++) {
            datasets[k]["data"] = aux[k];
        }
        fDatasets = datasets;
    } else {
        fLabels = labels;
        fDatasets = datasets;
    }
    
    new Chart(id, {
        type: "line",
        data: {
            labels: fLabels,
            datasets: fDatasets
        },
        options: {
            interaction: {
                intersect: false,
                mode: "index"
            },
            plugins: {
                legend: {
                    labels: {
                        useLineStyle: true
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

function drawChart(id, dataType, data, data2, hiddenEmpty, I18n, l) {
    var label, index, bd, bg;
    var dataset2 = null, datasets = null;
    var labels = [];
    var values = [];
    var values2 = [];
    
    switch(dataType) {
        case "BPM":
            index = "bpm";
            label = i18n("BPM", I18n, l);
            bd = "rgba(170, 20, 20, 1)";
            bg = "rgba(170, 20, 20, 1)";
            for (var i = 0; i <= 17; i++) values2.push(null);
            data2.forEach(function (value) {
                if (value["bpm"] !== null)
                    values2[value["code"]] = Number(value["bpm"]);
            });
            
            dataset2 = {
                label: i18n("WRISTWATCH_BPM", I18n, l),
                data: values2,
                borderColor: "rgba(170, 20, 20, 1)",
                backgroundColor: "rgba(170, 20, 20, 0.25)",
                borderDash: [10, 10],
                tension: 0.09,
                fill: false
            };
            break;
        case "Respiratory Rate":
            index = "respiratoryRate";
            label = i18n("RESPIRATORY_RATE", I18n, l);;
            bd = "rgba(20, 170, 170, 1)";
            bg = "rgba(20, 170, 170, 1)";
            break;
    }
    
    for (var i = 0; i <= 17; i++) labels.push(i);
    for (var i = 0; i <= 17; i++) values.push(null);
    
    data.forEach(function (value) {
        values[value["code"]] = Number(value[index]);
    });
    
    datasets = dataset2 !== null ?
        [{
            label: label,
            data: values,
            borderColor: bd,
            backgroundColor: bg,
            tension: 0.09,
            fill: false
        }, dataset2] : 
        [{
            label: label,
            data: values,
            borderColor: bd,
            backgroundColor: bg,
            tension: 0.09,
            fill: false
       }];
    
    drawChartInternal(id, labels, datasets, hiddenEmpty);
}

function drawAggrChart(id, dataType, data, hiddenEmpty, I18n, l) {
    var values = [], values2 = [], fvalues = [], fvalues2 = [];
    var dataset2 = null, datasets = null;
    var labels = [];
    var label, index, bd, bg, c = 0;

    switch(dataType) {
        case "BPM":
            index = "bpm";
            label = i18n("BPM_AVG", I18n, l);;
            bd = "rgba(170, 20, 20, 1)";
            bg = "rgba(170, 20, 20, 1)";
            break;
        case "Respiratory Rate":
            index = "respiratoryRate";
            label = i18n("RESPIRATORY_RATE_AVG", I18n, l);;
            bd = "rgba(20, 170, 170, 1)";
            bg = "rgba(20, 170, 170, 1)";
            break;
    }
    
    for (var i = 0; i <= 17; i++) {
        labels.push(i);
        values.push(null);
        values2.push(null);
    }
    
    data.forEach(function (dataCsv) {
        c++;
        var hasCodeZero = false;
        for (var i = 0; i < dataCsv[index].length; i++) {
            var value = dataCsv[index][i];
            if (value["code"] === 0)
                hasCodeZero = true;
            if (value[index] === null && value["code"] === 0) {
                if (value[index] !== null) {
                    if (values[value["code"]] === null)
                        values[value["code"]] = 0.0;
                    values[value["code"]] = values[value["code"]] +
                            Number(dataCsv[index][i + 1][index]);
                }
            } else {
                if (value[index] !== null) {
                    if (values[value["code"]] === null)
                        values[value["code"]] = 0.0;
                    values[value["code"]] = values[value["code"]] +
                            Number(value[index]);
                }
            }
        }

        if (dataType === "BPM") {
            for (var i = 0; i < dataCsv["wristwatchBpm"].length; i++) {
                var value = dataCsv["wristwatchBpm"][i];
                if (value["code"] === 0)
                if (value[index] === null && value["code"] === 0) {
                    if (value[index] !== null) {
                        if (values2[value["code"]] === null)
                            values2[value["code"]] = 0.0;
                        values2[value["code"]] = values2[value["code"]] +
                                Number(dataCsv["wristwatchBpm"][i + 1]["bpm"]);
                    }
                } else {
                    if (value[index] !== null) {
                        if (values2[value["code"]] === null)
                            values2[value["code"]] = 0.0;
                        values2[value["code"]] = values2[value["code"]] +
                                Number(value["bpm"]);
                    }
                }
            }
        }
    });
    
    values.forEach(function (value) {
        if (value !== null)
            fvalues.push(value / c);
        else
            fvalues.push(null);
    });
    
    if (dataType === "BPM") {
        values2.forEach(function (value) {
            if (value !== null)
                fvalues2.push(value / c);
            else
                fvalues2.push(null);
        });
        dataset2 = {
            label: i18n("WRISTWATCH_BPM_AVG", I18n, l),
            data: fvalues2,
            borderColor: bd,
            backgroundColor: "rgba(170, 20, 20, 0.25)",
            borderDash: [10, 10],
            tension: 0.09,
            fill: false
        };
    }
    
    datasets = dataset2 !== null ?
        [{
            label: label,
            data: fvalues,
            borderColor: bd,
            backgroundColor: bg,
            tension: 0.09,
            fill: false
        }, dataset2] :
        [{
            label: label,
            data: fvalues,
            borderColor: bd,
            backgroundColor: bg,
            tension: 0.09,
            fill: false
        }];
    
    drawChartInternal(id, labels, datasets, hiddenEmpty);
}

function drawGpChart(id, dataType, data, hiddenEmpty, I18n, l) {
    var datasets = [], labels = [];
    var index, c = 0;
    var colors = [
        "rgba(0, 127, 127", "rgba(127, 0, 127", "rgba(127, 127, 0",
        "rgba(0, 127, 255", "rgba(127, 0, 255", "rgba(127, 255, 0",
        "rgba(0, 255, 127", "rgba(255, 0, 127", "rgba(255, 127, 0",
        "rgba(127, 127, 255", "rgba(127, 127, 255", "rgba(127, 255, 127",
        "rgba(127, 255, 127", "rgba(255, 127, 127", "rgba(255, 127, 127",
        "rgba(255, 127, 255", "rgba(127, 255, 255", "rgba(127, 255, 255",
        "rgba(255, 255, 127", "rgba(255, 255, 127", "rgba(255, 127, 255",
        "rgba(0, 255, 255", "rgba(255, 0, 255",  "rgba(255, 255, 0"
    ];

    switch(dataType) {
        case "BPM":
            index = "bpm";
            break;
        case "Respiratory Rate":
            index = "respiratoryRate";
            break;
    }
    
    for (var i = 0; i <= 17; i++) labels.push(i);
    
    data.forEach(function (dataCsv) {
        var values = [];
        for (var i = 0; i <= 17; i++) values.push(null);
        dataCsv[index].forEach(function (value) {
            values[value["code"]] = Number(value[index]);
        });
        if (values[0] === null) {
            values[0] = values[1];
        }
        datasets.push({
            label: dataCsv["id"],
            data: values,
            borderColor: colors[c] ? colors[c] + ", 1)" : "rgba(127, 127, 127" + ", 1)",
            backgroundColor: colors[c] ? colors[c] + ", 0" : "rgba(127, 127, 127" + ", 1)",
            tension: 0.09,
            fill: false
        });
        if (dataType === "BPM") {
            var values2 = [];
            dataCsv["wristwatchBpm"].forEach(function (value) {
                values2[value["code"]] = Number(value["bpm"]);
            });
            datasets.push({
                label: dataCsv["id"] + " (" + i18n("WRISTWATCH_BPM", I18n, l) + ")",
                data: values2,
                borderColor: colors[c] ? colors[c] + ", 1)" : "rgba(127, 127, 127" + ", 1)",
                backgroundColor: colors[c] ? colors[c] + ", 0" : "rgba(127, 127, 127" + ", 0.25)",
                borderDash: [10, 10],
                tension: 0.09,
                fill: false
            });
        }
        c++;
    });

    drawChartInternal(id, labels, datasets, hiddenEmpty);
}

function setDownload(pcap, id) {
    $(id).attr("href", "PCAP/" + pcap);
}

function titleState(id, icon, line) {
    var state;
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
    var state;
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
    var state;
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
    var state;
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