extern printf:near
.intel_syntax noprefix
#
#	global variables
#
.data
.global g

g: QWORD 256

.global fxp2
fxp2  db  'a = %I64d b = %I64d c = %I64d d = %I64d e = %I64d sum = %I64d\n', 0AH, 00H     # string in code section,  OAH = LF
.global fxp3
fxp3 db   'a = %I64d b = %I64d a*b = %I64d\n', 0BH, 00H #


#
#	_int64 g
#

#*******
.text
.global p

p:  push rbp
	mov rbp, rsp
	sub rsp, 4

	mov rax, [rdi]
	add rax, [rsi]
	mov [rbp - 4], rax
	shl rax, 2
	dec rax

	mov rsp, rbp
	pop rbp
	ret

#parameter j rdx
#

  					# make sure func is exported
#
#	_int64 q(_int64 i)
#	{
#		return p(g, -i)#
#	}

#
#	parameter i rcx
#

.global q					# make sure func is exported
# parameter i: on linux will be on rdi
q:	push rbp
	mov rbp, rsp
	#sub rsp, 4			no locals
	#standard calling convention
	mov rax, [rdi]
	neg rax
	mov rdi, [g]	# moving parameter to correct reg
	mov rsi, [rax]				# rdi is where first param goes
	call p

	# standard exit code
	mov rsp, rbp
	pop rbp
	ret



#
#	_int 64 xp5(_int64 a, _int64 b, _int64 c, _int64 d, _int64 e){
#		int sum = a+b+c+d+e#
#		printf("a = %I64d b = %I64d c = %I64d d = %I64d e = %I64d sum = %I64d\n", a,b,c,d,e,sum#)
#	}
#
#	param a rcx
#	param b rdx
#	param c r8
#	param d r9
#	param e [rsp+96]
	
.global f
# one param
f:
	push rbp
	mov rbp, rsp

	mov rax, [rdi]		# get N from reg
	cmp rax, 0 			# if( n>0)
	jle base			# jump to base case
	dec rax
	mov rdi, [rax]
	call f
	mul rax, rdi 		# n * f(n-1)

	# standard exit code
	mov rsp, rbp
	pop rbp
	ret

.global fxp2
fxp2  db  'a = %I64d b = %I64d c = %I64d d = %I64d e = %I64d sum = %I64d\n', 0AH, 00H     # string in code section,  OAH = LF
.global fxp3
fxp3 db   'a = %I64d b = %I64d a*b = %I64d\n', 0BH, 00H #
.global xp5
# 5 params : 1 - RDI , 2: RSI ,3 :RDX, 4 : RCX, 5 : R8
# local var sum --
xp5:	push rbp
		mov rbp, rsp
		sub rsp, 4		# create space for local var

		mov rax, rdi
		add rax, rsi		# a + b 
		add rax, rdx		# a+b+c
		add rax, rcx		# a+b+c+d
		add rax, R8			# a+b+c+d+e = sum
		mov [rbp-4], rax	# store in local var sum
		push [rbp-4]		# push 7th param onto stack
		mov r9, [R8]		# e 6th
		mov r8, [RCX]		# d 5th
		mov RCX,[RDX]		# c 4th
		mov RDX,[rsi]		# b 3rd
		mov rsi,[rdi]		# a 2nd
		lea rdi, [fxp2]		# first param
		call printf
		pop					# pop off param

		lea rdi, [fxp3]		# load effective address
		mul rax, rsi, rdx 	# a*b 
		mov rdx, rax
		call printf			#

		mov rsp, rbp
		pop rbp
		ret
