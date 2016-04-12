.word 0

# write(1, hello, 6);
movl $args1, ap
chmk $4


# r1 = hello;
# *(uint8_t *)r1 = 'H';
movl $hello, r1
movb $'H, (r1)

# write(1, hello, 6);
movl $args1, ap
chmk $4


# r1 = hello;
# *(uint16_t *)(r1 + 2) = 'L'|'L'<<8;
movl $hello, r1
movw $'L|'L<8, 2(r1)

# write(1, hello, 6);
movl $args1, ap
chmk $4


# exit(0);
movl $args2, ap
chmk $1


.data
args1: .long 3, 1, hello, 6
args2: .long 1, 0
hello: .byte 'h, 'e, 'l, 'l, 'o, 10
