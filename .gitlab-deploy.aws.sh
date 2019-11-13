# !/bin/bash
# Get servers list:
set — f
# Variables from GitLab server:
# Note: They can’t have spaces!!
string=$DEPLOY_SERVER
array=(${string//,/ })
# Iterate servers for deploy and pull last commit
# Careful with the ; https://stackoverflow.com/a/20666248/1057052
for i in “${!array[@]}”; do
  echo “Deploy project on server ${array[i]}”
  ssh ubuntu@${array[i]} “cd ws-bank && git stash && git pull origin feature-virtualaccount && sudo docker build -t wsbank . && sudo docker run --rm -p 8080:8080 wsbank”
done