#!/bin/bash

javac --class-path src -d out src/base/*.java src/markup/*.java src/Main.java && java --class-path out -ea Main

