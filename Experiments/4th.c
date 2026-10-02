#include <stdio.h>

int main()
{
    int nums[] = {3, 1, 2, 2, 2, 1, 3};
    int k = 2;

    int n = sizeof(nums) / sizeof(nums[0]);

    int count = 0;

    for (int i = 0; i < n; i++)
    {
        for (int j = i + 1; j < n; j++)
        {
            if (nums[i] == nums[j])
            {
                if ((i * j) % k == 0)
                {
                    count++;
                }
            }
        }
    }

    printf("Number of valid pairs = %d\n", count);

    return 0;
}