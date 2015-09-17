#!/usr/bin/env bash

arch=vax-netbsdelf
as=$arch-as
dis="$arch-objdump -d"

rm -f allopr.d
mkdir -p tmp
cd tmp

func() {
    $as -o $1.o $1.s
    $dis $1.o > $1.d
    head -n8 $1.d | tail -n1 | cut -f2- >> ../allopr.d
}

data=`for i in {1..32}; do echo -n ", 0x50"; done`

cat ../allop-c.txt | while read line
do
    echo $line
    set -- $line
    fn=$1
    c=$2
    hex=`echo 0x$fn | sed 's/-/, 0x/'`
    if [ $c -eq 0 ]
    then
        echo ".byte $hex$data" > $fn.s
        func $fn
    else
        i=1
        while [ $i -le $c ]
        do
            echo -n ".byte $hex" > $fn-$i.s
            j=1
            while [ $j -lt $i ]
            do
                echo -n ", 0x50" >> $fn-$i.s
                j=`expr $j + 1`
            done
            echo ", 0x8f$data" >> $fn-$i.s
            func $fn-$i
            i=`expr $i + 1`
        done
    fi
done
