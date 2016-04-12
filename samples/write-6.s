.word 0

# write(1, hello, 6);
movl $args1, ap
chmk $4


# *(uint32_t *)hello = 'H'|'E'<<8|'L'<<16|'L'<<24;
movl $'H|'E<8|'L<16|'L<24, hello

# write(1, hello, 6);
movl $args1, ap
chmk $4


# *(uint8_t *)(hello + 4) = 'O';
movb $'O, hello + 4

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
