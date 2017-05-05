<!doctype html>

<html>

    <head>

        <meta charset="utf-8">

        <style>



        </style>

        <script src="https://img.hcharts.cn/jquery/jquery-1.8.3.min.js"></script>

        <script src="https://img.hcharts.cn/highcharts/highcharts.js"></script>

        <script src="https://img.hcharts.cn/highcharts/modules/exporting.js"></script>

    </head>

    <body>
<div id="container" style="min-width:400px;height:400px;"></div>
<button id="button" class="autocompare">Add series</button>


        <script>$(function () {
    // create the chart
    $('#container').highcharts({
        chart: {
            events: {
                addSeries: function () {
                    var label = this.renderer.label('A series was added, about to redraw chart', 100, 120)
                    .attr({
                        fill: Highcharts.getOptions().colors[0],
                        padding: 10,
                        r: 5,
                        zIndex: 8
                    })
                    .css({
                        color: '#FFFFFF'
                    })
                    .add();
                    setTimeout(function () {
                        label.fadeOut();
                    }, 1000);
                }
            }
        },
        xAxis: {
            categories: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec']
        },
        series: [{
            data: [29.9, 71.5, 106.4, 129.2, 144.0, 176.0, 135.6, 148.5, 216.4, 194.1, 95.6, 54.4]
        }]
    });
    // activate the button
    $('#button').click(function () {
        var chart = $('#container').highcharts();
        chart.addSeries({
            data: [216.4, 194.1, 95.6, 54.4, 29.9, 71.5, 106.4, 129.2, 144.0, 176.0, 135.6, 148.5]
        });
        $(this).attr('disabled', true);
    });
});

        </script>

	
	
	
	
	

    </body>

</html>