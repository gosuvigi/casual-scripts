You just started working for CoolNewCompany which is developing mathematics
related software. Since you are new to the team, your boss gives you an easy
task to test your abilities. Write a class that pretty-prints polynomials, 
following some simple rules:

    if a coefficient is 1, it doesn’t get printed
    if a coefficient is negative, you have to display something like "- 2x^3",
    	not "+ -2x^3"
    if a coefficient is 0, nothing gets added to the output
    for x^1 the ^1 part gets omitted
    x^0 == 1, so we don’t need to display it

Here’s a couple of usage examples:

puts Polynomial.new([-3,-4,1,0,6]) # => -3x^4-4x^3+x^2+6
puts Polynomial.new([1,0,2]) # => x^2+2

Don’t concern yourself too much with error handling, but if somebody tries 
to create a polynomial with less than 2 elements, your program has to raise
an ArgumentError with the message "Need at least 2 coefficients."