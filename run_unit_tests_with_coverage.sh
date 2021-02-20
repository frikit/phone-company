#!/bin/sh

sbt clean coverage test coverageAggregate
