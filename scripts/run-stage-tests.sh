#!/usr/bin/env bash
set -euo pipefail

test_output_dir="out/stage-tests"
rm -rf "${test_output_dir}"
mkdir -p "${test_output_dir}"

javac \
  -encoding UTF-8 \
  -source 17 \
  -target 17 \
  -d "${test_output_dir}" \
  src/*.java \
  tests/StageTestRunner.java

java -ea -cp "${test_output_dir}" StageTestRunner
