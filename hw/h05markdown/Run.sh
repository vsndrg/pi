#!/bin/bash

javac --class-path src -d out src/base/*.java src/markup/*.java && java --class-path out -ea markup.MarkupTest Base

