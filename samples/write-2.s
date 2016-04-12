.word 0

# write(1, hello, 6);
movl $args1, ap
chmk $4


# r1 = hello;
# *(uint32_t *)(r1 + 1) = 'E'|'L'<<8|'L'<<16|'O'<<24;
movl $hello, r1
movl $'E|'L<8|'L<16|'O<24, 1(r1)

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
