#include<bits/stdc++.h>
using namespace std;
#define mp make_pair
typedef long long ll;
#define MOD 1300031
vector<vector<pair<ll,ll>>> g;
vector<ll> sum1,sum2,cnt;
vector<int> visited;
ll n;
void dfs1(ll node)
{
    visited[node] = 1;
    for(int i = 0;i<g[node].size();i++)
    {
        ll x = g[node][i].first, w = g[node][i].second;
        if(visited[x]) continue;
        dfs1(x);
        sum1[node] += sum1[x] + cnt[x]*w;
        cnt[node] += cnt[x];
    }
    cnt[node]++;
}
void dfs2(ll node)
{
    if(node == 1)
    {
        sum2[node] = sum1[node];
    }
    visited[node] = 1;
    for(int i=0;i<g[node].size();i++)
    {
        ll x = g[node][i].first, w = g[node][i].second;
        if(visited[x]) continue;
        sum2[x] =   sum2[node]  - cnt[x]*w + w*(n-cnt[x]);
        dfs2(x);
    }
}
int main()
{
    int t,x,y,w;
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    cin>>t;
    while(t--)
    {
        cin>>n;
        g.resize(n+1);sum1.resize(n+1);sum2.resize(n+1);
        visited.resize(n+1);cnt.resize(n+1);
        for(int i=0;i<n-1;i++)
        {
            cin>>x>>y>>w;
            g[x].push_back(mp(y,w));
            g[y].push_back(mp(x,w));
        }
        dfs1(1);
        visited.clear();
        visited.resize(n+1);
        dfs2(1);
        double ans = 0;
        for(int i=1;i<=n;i++)
        {
            ans+=(double)sum2[i]/2;
        }
        ll fans = (ll)ans;
        cout<<fans%MOD<<"\n";
        sum1.clear();sum2.clear();visited.clear();cnt.clear();g.clear();
    }

}