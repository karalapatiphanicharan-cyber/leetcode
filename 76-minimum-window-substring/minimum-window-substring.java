class Solution 
{
    public String minWindow(String s, String t) 
    {
        if(s.length()<t.length())
        {
            return "";
        }
        int freq [] = new int[128];
        for(char ch : t.toCharArray())
        {
            freq[ch]++;
        }
        int left=0;
        int right=0;
        int count=t.length();
        int minlength = Integer.MAX_VALUE;
        int start = 0;
        while(right<s.length())
        {
            char ch =s.charAt(right);
            if(freq[ch]>0)
            {
                count--;
            }
            freq[ch]--;
            right++;
            while(count==0)
            {
            if(right-left < minlength)
            {
                minlength = right-left;
                start = left;
            }
            char leftchar = s.charAt(left);
            freq[leftchar]++;
            if(freq[leftchar]>0)
            {
                count++;
            }
            left++;
            }
        }
        if (minlength == Integer.MAX_VALUE)
        {
            return "";
        }

        return s.substring(start, start + minlength);
    }
}