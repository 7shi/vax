.word 0

# write(1, hello, 6);
movl $args1, ap
chmk $4


# r1 = hello;
# r0 = 'H'|'E'<<8
# *(uint8_t *)r1 = r0;
movl $hello, r1
movl $'H|'E<8, r0
movb r0, (r1)

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
