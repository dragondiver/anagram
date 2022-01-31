#!/bin/bash

root_folder=$(cd $(dirname $0); cd ..; pwd)

exec 3>&1


npm run docs:deploy