<h2><a href="https://leetcode.com/problems/partition-to-k-equal-sum-subsets/">698. Partition to K Equal Sum Subsets<a id="solution_btn_698" href="https://labuladong.gitee.io/plugin-v2/?qno=698" target="_blank" class="button-4" style="font-weight: bold; margin-left: 10px;">labuladong 题解</a><a id="brief_btn_698" href="#" target="_blank" class="button-4" style="font-weight: bold; margin-left: 10px;">思路</a></a></h2><h3>Medium</h3><hr><div><p>Given an integer array <code>nums</code> and an integer <code>k</code>, return <code>true</code> if it is possible to divide this array into <code>k</code> non-empty subsets whose sums are all equal.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre><strong>Input:</strong> nums = [4,3,2,3,5,2,1], k = 4
<strong>Output:</strong> true
<strong>Explanation:</strong> It's possible to divide it into 4 subsets (5), (1, 4), (2,3), (2,3) with equal sums.
</pre>

<p><strong>Example 2:</strong></p>

<pre><strong>Input:</strong> nums = [1,2,3,4], k = 3
<strong>Output:</strong> false
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= k &lt;= nums.length &lt;= 16</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>4</sup></code></li>
	<li>The frequency of each element is in the range <code>[1, 4]</code>.</li>
</ul>
</div>