#! /bin/bash
read -a arr  
len=${#arr[@]}    
for((i=0;i<len;i++))    
do
    for((j=0;j<len-i-1;j++))
    do     
        if [[ ${arr[$j]}  -gt ${arr[j+1]} ]]  
        then
              term=${arr[$j]}
              arr[$j]=${arr[j+1]}
              arr[j+1]=$term
        fi
    done
done
 
for((k=0;k<len;k++))
do
    echo ${arr[$k]}
done
