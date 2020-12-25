#!/bin/bash

function brokers_waiting {
  EXPECTED_NUM_BROKERS=$1
  COUNTER=0

  echo -e "Check kafka brokers"

  while [[ $(("$(zookeeper-shell.sh zookeeper:2181 ls /brokers/ids 2>/dev/null | grep -o , | wc -l)" + 1)) != "$EXPECTED_NUM_BROKERS" ]]; do
    sleep 1
    COUNTER=$((COUNTER+1))
    if [[ $COUNTER -gt 60 ]]
    then
      echo -e "Not all kafka brokers went up"
      exit 1
    fi
  done
}

function main {
  brokers_waiting 1
  kafka-topics.sh --create --if-not-exists --zookeeper zookeeper:2181 --topic t.svfc.incident.claim.receiver.event --partitions 1 --replication-factor 1
}

main &

