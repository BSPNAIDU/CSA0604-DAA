#include <stdio.h>
#include <string.h>

int isPalindrome(char str[])
{
    int left = 0;
    int right = strlen(str) - 1;

    while (left < right)
    {
        if (str[left] != str[right])
        {
            return 0;
        }

        left++;
        right--;
    }

    return 1;
}

int main()
{
    char words[][20] = {
        "abc",
        "car",
        "ada",
        "racecar",
        "cool"
    };

    int n = 5;

    for (int i = 0; i < n; i++)
    {
        if (isPalindrome(words[i]))
        {
            printf("First palindromic string: %s\n", words[i]);
            return 0;
        }
    }

    printf("No palindromic string found\n");

    return 0;
}