MIMS64 MEM SIM

Is a MIPS64 simulator that uses a single shared memory model. Wherein both the code and data segments reside on the same memory. Whereas the code segment is accessible to itself. Allowing the source code to edit itself, something that is not featured in other simulators such as EduMIPS.

Methodology
1. convert the whole source file into binary where the code segment instructions will be transformed into its opcode
2. the starting address of the program counter is the pointer to the address of the first instruction in the code segment
3. 32bits are read from every pc and decoded
4. then executed

sample codes:

a code that jumps into data segment
notice that the memory location L1 points to a data which is also an opcode if "daddu r1, r2, r3" such that when j jumps to it daddu r1,r2,r3 will be executed, even though L1 is alread outside .code segment and is inside .data segment

```
	.data
L1:	00
	43
	08
	2D
	.code
start:	daddu r4, r5, r6
	daddu r7, r8, r9
	j L1
	daddu r10, r11, r12
	daddu r13, r14, r15
	daddu r16, r17, r18
```

NOTES: 
opcode generation for j
- shifting right by 2 (div 4) the target address is not performed
opcode reading for j
- shifting to left by 2 (mul 4) the target address is not performed in EX/MEM
* reason for this is because of the "un-aligned" memory access wherein address 0x02 can be accessed and when shifted right by 2 (div4) will result to 0, an  un recoverable address when shifted left by 2 (mul4)
