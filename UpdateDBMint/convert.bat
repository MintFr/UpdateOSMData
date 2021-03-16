@ECHO ON


REM convert data
del data_nantes_api_converties.osm
osmconvert  data_nantes_api.osm.pbf --drop-author --drop-version --out-osm -o=data_nantes_api_converties.osm

exit


