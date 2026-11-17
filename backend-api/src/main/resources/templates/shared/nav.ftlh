<nav>
    <div class="navbar">
        <a href="/" class="navbar-brand"><img src="/MealPrepMatch.png" width="150" height="150" alt="MealPrep Match"></a>
        <ul class="nav-links">
            <li><a href="/">Home</a></li>
            <#if loggedIn!false>
                <li><a href="/ui/consumers/profile">Your Dashboard</a></li>
                <li><a href="/login.html" class="btn btn-primary">Logout</a></li>
            <#else>
                <li><a href="/login.html" class="btn btn-primary">Login</a></li>
            </#if>
        </ul>
    </div>
</nav>