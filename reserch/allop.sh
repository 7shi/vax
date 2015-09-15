#!/usr/bin/env bash

dest=allop.d
arch=vax-netbsdelf
as=$arch-as
dis="$arch-objdump -d"

rm -f $dest
mkdir -p build
cd build

func() {
    echo $1
    echo ".byte $2, 0x50, 0x51, 0x52, 0x53, 0x54, 0x55" > $1.s
    $as -o $1.o $1.s
    $dis $1.o > $1.d
    head -n 8 $1.d | tail -n 1 | sed 's/^.*:\s*//' >> ../$dest
}

for i in {0..252}
do
    hex=`printf "%02x" $i`
    func $hex "0x$hex"
done

for i in {0..255}
do
    hex=`printf "%02x" $i`
    func "fd-$hex" "0xfd, 0x$hex"
done

for i in {0..255}
do
    hex=`printf "%02x" $i`
    func "fe-$hex" "0xfe, 0x$hex"
done

for i in {0..255}
do
    hex=`printf "%02x" $i`
    func "ff-$hex" "0xff, 0x$hex"
done
