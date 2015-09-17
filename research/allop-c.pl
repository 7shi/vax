#!/usr/bin/env perl

while (<>) {
    chop while substr($_, -1) le " ";
    @f = split(/\t/);
    @b = split(/ /, $f[0]);
    if ($f[1] =~ /^(.*?) (.*)/) {
        $mne = $1;
        @opr = split(/,/, $2);
    } else {
        $mne = $f[1];
        @opr = ();
    }
    if ($mne ne ".word") {
        $c = @opr;
        $r = "";
        if ($c > 0) {
            $l = length($opr[$c - 1]);
            if ($l > 2) {
                --$c;
                $r = ($l - 2) / 2;
            }
        }
        $op = hex($b[0]) < 0xfd ? $b[0] : "$b[0]-$b[1]";
        print "$op\t$c\t$r\t$mne\n";
    }
}
