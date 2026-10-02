#include <stdio.h>

int main()
{
    int nums[] = {1, 2, 3, 4, 5};

    int n = sizeof(nums) / sizeof(nums[0]);

    if (n == 0)
    {
        printf("List is empty\n");
        return 0;
    }

    int max = nums[0];

    for (int i = 1; i < n; i++)
    {
        if (nums[i] > max)
        {
            max = nums[i];
        }
    }

    printf("Maximum element = %d\n", max);

    return 0;
}