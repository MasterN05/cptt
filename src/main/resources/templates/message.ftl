<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>中奖通知</title>
</head>

<style type="text/css">
    table {
        font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
        width: 100%;
        border-collapse: collapse;
        text-align: center;
    }

    td, th {
        font-size: 1em;
        border: 1px solid #5B4A42;
        padding: 3px 7px 2px 7px;
    }

    th {
        font-size: 1.1em;
        text-align: center;
        padding-top: 5px;
        padding-bottom: 4px;
        background-color: #24A9E1;
        color: #ffffff;
    }
</style>
<body>
<div>
    <h2>中奖信息</h2>
    <table id="customers">
        <tr>
            <th>期次</th>
            <th>中奖号码</th>
            <th>我的号码</th>
            <th>信息</th>
        </tr>
        <tr>
            <td>${(params.period)!""}</td>
            <td>${(params.lotteryNumber)!""}</td>
            <td>${(params.myNumber)!""}</td>
            <td>${(params.msg)!""}</td>
        </tr>
    </table>
</div>
</body>
</html>