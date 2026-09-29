class Solution {
    public int ladderLength(String beginWord,String endWord,List<String> wordList) {
        Set<String> set=new HashSet<>(wordList);
        if(!set.contains(endWord)) return 0;
        Queue<String> q=new LinkedList<>();
        q.offer(beginWord);
        int level=1;

        while(!q.isEmpty()) {
            int size=q.size();

            for(int s=0;s<size;s++) {
                String word=q.poll();
                if(word.equals(endWord)) return level;

                char[] arr=word.toCharArray();

                for(int i=0;i<arr.length;i++) {
                    char original=arr[i];

                    for(char c='a';c<='z';c++) {
                        arr[i]=c;
                        String next=new String(arr);

                        if(set.contains(next)) {
                            q.offer(next);
                            set.remove(next);
                        }
                    }

                    arr[i]=original;
                }
            }
            level++;
        }
        return 0;
    }
}