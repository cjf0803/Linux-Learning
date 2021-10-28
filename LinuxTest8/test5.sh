#!bin/bash
sum=0
for(( i=0; i<101; i++ ))
do
  (( sum += i ))
done
echo $sum
