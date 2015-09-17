#!/usr/bin/env bash

arch=vax-netbsdelf
as=$arch-as
dis="$arch-objdump -d"

rm -f allop.d
mkdir -p tmp
cd tmp

func() {
    echo $1
    echo ".byte $2, 0x50, 0x50, 0x50, 0x50, 0x50, 0x50" > $1.s
    $as -o $1.o $1.s
    $dis $1.o > $1.d
    head -n8 $1.d | tail -n1 | cut -f2- >> ../allop.d
}

for i in {0..252}
do
    hex=`printf "%02x" $i`
    func $hex "0x$hex"
done

for i in {253..255}
do
    hex1=`printf "%02x" $i`
    for j in {0..255}
    do
        hex2=`printf "%02x" $j`
        func "$hex1-$hex2" "0x$hex1, 0x$hex2"
    done
done
