# Password-Hashing
# The program takes N size input string, then the program will insert the string into a matrix .as the program will  compute the offset of said matrix ,by getting the square root of string size , we obtain the number of rows to generate a matrix of offset x N.
# After that, the program will generate a random number matrix between -10 and 100. As these two matrices will be multiplied leading to create an encrypted password. Then we calculate the 
determinant to check for inverse if it is positive, then we inverse our result and we multiply it with the encrypted matrix to get our original string.
# To hash the password, we get the transpose of the encrypted matrix and multiply the transpose matrix with the encrypted one.  Then the program will generate a hash value using this formula: 
# hashValue=getHashed.length*(offset+lenghtofPass)
# The program will compute that hashed value by this formula: 
# Arrays.hashCode(getHashed)*(hashValue+(int)deter) 
# Finally ,we obtain the hashed value.

