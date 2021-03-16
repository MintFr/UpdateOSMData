@ECHO OFF


REM export osm data from overpass api
REM download unfiltered data from city Nantes

del data_nantes_api.osm.pbf
curl -o data_nantes_api.osm.pbf https://overpass-api.de/api/map?bbox=-1.7183,47.1458,-1.3695,47.2855

exit