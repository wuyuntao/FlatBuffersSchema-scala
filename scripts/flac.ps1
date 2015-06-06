$RootPath = "$PSScriptRoot\.."
$ProjectPath = "$RootPath\flatbuffers-schema"
$FlatcPath = "$ProjectPath\lib\flatc.exe"

$CoreSchemaPath = "$ProjectPath\src\main\resources\schemas"
$CoreOutputPath = "$ProjectPath\src\main\java"

$TestSchemaPath = "$ProjectPath\src\test\resources\schemas"
$TestOutputPath = "$ProjectPath\src\test\java"

# Generate schema for core lib
pushd $CoreSchemaPath

$SchemaFiles = ls *.fbs
cmd /c $FlatcPath -j -o $CoreOutputPath $SchemaFiles 

popd

# Generate schema for test lib
pushd $TestSchemaPath

$SchemaFiles = ls *.fbs
cmd /c $FlatcPath -j -o $TestOutputPath $SchemaFiles 

popd