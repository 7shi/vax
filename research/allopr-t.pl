#!/usr/bin/env perl

%im = (
    '$0x50'                                         => 'b',
    '$0x5050'                                       => 'w',
    '$0x50505050'                                   => 'l',
    '$0x50505050 [f-float]'                         => 'f',
    '$0x5050505050505050'                           => 'q',
    '$0x5050505050505050 [d-float]'                 => 'd',
    '$0x5050505050505050 [g-float]'                 => 'g',
    '$0x50505050505050505050505050505050'           => 'o',
    '$0x50505050505050505050505050505050 [h-float]' => 'h'
);

$cur = "";
@opt = ();

sub output {
    if ($cur ne "") {
        print "$cur\t$mne\t", join("", @opt), "\n";
        @opt = ();
    }
}

while (<>) {
    chop while substr($_, -1) lt " ";
    @f = split(/\t/);
    @b = split(/ /, $f[0]);
    $op = hex($b[0]) < 0xfd ? $b[0] : "$b[0]-$b[1]";
    if ($cur ne $op) {
        output();
        $cur = $op;
    }
    if ($f[1] =~ /^(.*?) (.*)/) {
        $mne = $1;
        @opr = split(/,/, $2);
    } else {
        $mne = $f[1];
        @opr = ();
    }
    $c = @opr;
    for (my $i = 0; $i < $c; ++$i) {
        $o = $opr[$i];
        $t = $im{$o};
        if ($t ne "") {
            $opt[$i] = $t;
        } elsif ($o =~ /^0x..$/) {
            $opt[$i] = "1";
        } elsif ($o =~ /^0x....$/) {
            $opt[$i] = "2";
        }
    }
}
output();
