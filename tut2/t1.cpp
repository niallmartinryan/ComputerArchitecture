
#include <iostream>		// cout
#include "t1.h"

int main()
{
	std::cout << "g = " << g << std::endl;
	//	Test p's output
	std::cout << "p(1, 2) - Expected: 11; \n\tActual " << p(1, 2) << ";" << std::endl;

	//	Test q's output
	std::cout << "q(5) - Expected: 1003; \n\tActual " << q(5) << ";" << std::endl;
	std::cout << "q(-1) - Expected: 1027; \n\tActual " << q(-1) << ";" << std::endl;

	//	Test f's output
	std::cout << "f(1) - Expected: 1; \n\tActual " << f(1) << ";" << std::endl;
	std::cout << "f(2) - Expected: 2; \n\tActual " << f(2) << ";" << std::endl;
	std::cout << "f(3) - Expected: 6; \n\tActual " << f(3) << ";" << std::endl;
	std::cout << "f(4) - Expected: 24; \n\tActual " << f(4) << ";" << std::endl;
	std::cout << "f(5) - Expected: 120; \n\tActual " << f(5) << ";" << std::endl;
	std::cout << "f(6) - Expected: 720; \n\tActual " << f(6) << ";" << std::endl;
	std::cout << "f(7) - Expected: 5040; \n\tActual " << f(7) << ";" << std::endl;
	std::cout << "f(8) - Expected: 40320; \n\tActual " << f(8) << ";" << std::endl;
	std::cout << "f(9) - Expected: 362880; \n\tActual " << f(9) << ";" << std::endl;
	std::cout << "f(10) - Expected: 3628800; \n\tActual " << f(10) << ";" << std::endl;
	std::cout << "f(11) - Expected: 39916800; \n\tActual " << f(11) << ";" << std::endl;
	std::cout << "f(12) - Expected: 479001600; \n\tActual " << f(12) << ";" << std::endl;
	std::cout << "f(13) - Expected: 6227020800; \n\tActual " << f(13) << ";" << std::endl;
}