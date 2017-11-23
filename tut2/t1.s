#	Computer Architecture tutorial 1
#	For all global variables, need to underscore the labels
#	Include intel syntax stuff
	.intel_syntax noprefix
#	Declare global variables
	.data
	.global _g

_g: .long 256

#	Let the code begin
	.text

.global _p
#	subroutine p
#	parameters:
#		i - [ebp + 8]
#		j - [epb + 12]
#	Local variables:
#		k - [epb - 4]

	#"Standard" entry code for a function
_p:	push ebp 	# save ebp
	mov ebp, esp	# create a new stack frame
	sub esp, 4	# allocate space for local variables

	mov eax, [ebp + 8]	
	add eax, [ebp + 12] # k = i + j
	mov [ebp - 4], eax	# store in ebp - 4
	shl eax, 2
	dec eax

	#Standard exit code for a function
	mov esp, ebp	# restore esp
	pop ebp		# restore previous ebp
	ret 	# return from the function

.global _q
#	subroutine q
#	parameters:
#		i - [ebp + 8]

_q:	push ebp 	# frame pointer onto the stack
	mov ebp, esp
	# don't need sub esp, 4 because there's no local variables

	mov eax, [ebp + 8]	# invert 
	neg eax
	push eax
	push _g
	call _p 		# call the function

	# Exit code
	mov esp, ebp	# restore esp
	pop ebp		# restore previous ebp
	ret 	# return from function

.global _f
#	subroutine f
#	parameters:
#		n - [ebp + 8]

_f:	push ebp 	# save ebp
	mov ebp, esp

	mov eax, [ebp + 8]	# n
	cmp eax, 0	# if( n > 0 )
	jle base 	# jump to base case
	dec eax 	# n--
	push eax	# push parameter onto stack
	call _f 	# call recursive function f
	mul dword ptr [ebp + 8]	# multiply eax by n

	# Exit code for function
	mov esp, ebp	# restore esp
	pop ebp		# restore previous ebp
	ret 	# return from the function
base:
	mov eax, 1

	# Exit code for function
	mov esp, ebp	# restore esp
	pop ebp		# restore previous ebp
	ret 	# return from the function
	