@ECHO ON


REM import data to database for cars
osm2pgrouting --clean = 1 -f data_nantes_api_converties.osm -d routing_cars -p 5432 -h localhost --username postgres --password jehfp79/_8d3( --conf mapconfig\mapconfig_for_cars.xml --addnodes --attributes --tags


REM import data to database for pedestrian
osm2pgrouting --clean = 1 -f data_nantes_api_converties.osm -d routing_pedestrian -p 5432 -h localhost --username postgres --password jehfp79/_8d3( --conf mapconfig\mapconfig_for_pedestrian.xml --addnodes --attributes --tags


REM import data to database for bicycles
osm2pgrouting --clean = 1 -f data_nantes_api_converties.osm -d routing_bicylces -p 5432 -h localhost --username postgres --password jehfp79/_8d3( --conf mapconfig\mapconfig_for_bicycles.xml --addnodes --attributes --tags


REM import data to database for public transport
osm2pgrouting --clean = 1 -f data_nantes_api_converties.osm -d routing_public_transport -p 5432 -h localhost --username postgres --password jehfp79/_8d3( --conf mapconfig\mapconfig_for_public_transport.xml --addnodes --attributes --tags


exit