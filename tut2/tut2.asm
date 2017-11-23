extern printf:near
.intel_syntax noprefix
;
;	global variables
;
.data
.global g

g: long long 256

;
;	_int64 g
;

;*******
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

;parameter j rdx
;

  					; make sure func is exported
p:	lea rax, [rcx + rdx]
	shl	rax, 2
	dec	rax
	ret

;
;	_int64 q(_int64 i)
;	{
;		return p(g, -i);
;	}

;
;	parameter i rcx
;

public q					; make sure func is exported

q:	sub rsp, 32
	mov rdx, rcx
	neg rdx
	mov rcx, [g]
	call p
	add	rsp, 32
	ret













;
;	_int 64 xp5(_int64 a, _int64 b, _int64 c, _int64 d, _int64 e){
;		int sum = a+b+c+d+e;
;		printf("a = %I64d b = %I64d c = %I64d d = %I64d e = %I64d sum = %I64d\n", a,b,c,d,e,sum;)
;	}
;
;	param a rcx
;	param b rdx
;	param c r8
;	param d r9
;	param e [rsp+96]

