#!/bin/bash
 
#projects=$(gcloud projects list --format='value[terminator=" "](projectId)')
projects="bolcom-nonpro bolcom-pro"
 
for project in $projects; do
  ips="${ips} $(gcloud --project $project container clusters list --format='value[terminator=" "](endpoint)')"
  ips="${ips} $(gcloud --project $project compute addresses list --format='value[terminator=" "](address)')"
done
 
sshuttle -r shd-gcp-jump-001.bolcom.net $ips
