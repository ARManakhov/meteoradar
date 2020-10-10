<!DOCTYPE html>
<html lang="en">
<head>
    <title>device ${device.name}</title>
    <#include "head.ftl">
    <link rel="stylesheet" href="/css/device.css">
    <script src="https://cdn.jsdelivr.net/gh/openlayers/openlayers.github.io@master/en/v6.4.3/build/ol.js"></script>

</head>
<body>
<#include "navbar.ftl">

<div class="row justify-content-center">
    <div class="col-md-8">

        <div class="card mt-3">
            <header class="card-header">
                <h4 class="card-title mt-2">Device ${device.name}</h4>
            </header>
            <article class="card-body">
                <div class="row mt-3">Owner : ${device.owner.nickname}</div>
                <#if device.owner.id == client_user.id || client_user.role=="ADMIN">
                    <div class="row mt-3">Token : ${device.token}</div>
                    <div class="row mt-3">Description :
                        <form method="post" action="/device-description/${device.id}">
                            <input value="<#if device.description??>${device.description}</#if>" name="text">
                            <button>Update</button>
                        </form>
                    </div>
                <#else>
                    <div class="row mt-3">Description : <#if device.description??>${device.description}</#if></div>
                </#if>
            </article>
        </div>

        <div class="card mt-3">
            <header class="card-header">
                <h4 class="card-title mt-2">On map ${device.name}</h4>
            </header>
            <article class="card-body">
                <div id="map" class="map"></div>
            </article>
        </div>

        <div class="card mt-3">
            <header class="card-header">
                <h4 class="card-title mt-2">Sensors</h4>
            </header>
            <article class="card-body">

                <#list device.sensors as sensor>
                    <div class="card mt-3">
                        <header class="card-header">
                            <h4 class="card-title mt-2">${sensor.name}</h4>
                        </header>
                        <article class="card-body">
                            <div class="row mt-3">value : ${sensor.value}</div>
                            <div class="row mt-3">unit : ${sensor.unit}</div>
                            <div class="row mt-3">type : ${sensor.sensorType}</div>
                        </article>
                    </div>
                </#list>
            </article>
        </div>
    </div>
</div>
<script type="text/javascript">
    var map = new ol.Map({
        target: 'map',
        layers: [
            new ol.layer.Tile({
                source: new ol.source.OSM()
            })
        ],
        view: new ol.View({
            center: ol.proj.fromLonLat([37.41, 8.82]),
            zoom: 4
        })
    });
</script>
</body>
</html>