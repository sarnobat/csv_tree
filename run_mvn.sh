mvn --quiet compile
cat /sarnobat.garagebandbroken/Desktop/github-repositories/java_variableflow_csv/out.csv | mvn --quiet exec:java -Dexec.mainClass="CsvTree"