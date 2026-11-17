
<#assign mealCount = 0>
<#list consumer.mealPlanList as plan>
  <#assign mealCount += plan.mealsThisWeek?size>
</#list>
<#assign pageTitle="Your Consumer Dashboard">
<#include "/shared/head.ftlh">
<#include "/shared/nav.ftlh">

    <#if message??>
      <div class="alert alert-success mt-3">${message}</div>
    </#if>

    <section class="section text-center">
      <h1>Welcome Back, ${consumer.name}!</h1>
      <p>
        Explore your meal options and manage your orders with MealPrep Match.
      </p>
    </section>

    <section class="section">
      <div class="grid grid-3">
        <div class="card">
          <div class="card-body text-center">
            <h3>${mealCount}</h3>
            <p>Meals Ordered For This Week</p>
          </div>
        </div>

        <div class="card">
          <div class="card-body text-center">
            <#if consumer.mealPlanList?has_content>
              <h3>${consumer.mealPlanList?last.weeks} Weeks</h3>
              <p>Current Subscription Duration</p>
            <#else>
              <h3>None</h3>
              <p>No Active Subscription</p>
            </#if>
          </div>
        </div>

        <div class="card">
          <div class="card-body text-center">
            <h3>${consumer.reviews?size}</h3>
            <p>Reviews You've Written</p>
          </div>
        </div>
      </div>
    </section>

    <section class="section">
      <h2 class="section-title">Manage Your Meals</h2>
      <p>
        View all meals from the catalog, and add meals to your weekly order.
      </p>
      <a href="/pantry.html" class="btn btn-primary">
        View Pantry
      </a>
    </section>

    <section class="section">
      <h2 class="section-title">This Week's Meals</h2>
      <#if mealCount == 0>
        <p>You haven't added any meals yet.</p>
      <#else>
        <#list consumer.mealPlanList as plan>
          <#list plan.mealsThisWeek as meal>
            <div class="card-body">
              <div class="card" style="max-width: 1000px;">
                <div class="card-body">
                  <h5 class="card-title">${meal.name}</h5>
                  <p class="card-text">${meal.description!""}</p>
                  <p class="card-text">
                    <small>${meal.calories} cal · ${meal.protein}g protein · ${meal.carbohydrates}g carbs · ${meal.fat}g fat</small>
                  </p>
                  <a href="/mealdetails.html" class="btn btn-primary">
                    View Meal
                  </a>
                </div>
              </div>
            </div>
          </#list>
        </#list>
      </#if>
    </section>

    <section class="section">
      <h2 class="section-title">Your Profile</h2>
      <div class="card">
        <div class="card-body">
          <p><strong>Email:</strong> ${consumer.email}</p>
          <p><strong>Phone:</strong> ${consumer.phoneNumber!"—"}</p>
          <p><strong>Account status:</strong> ${consumer.accountStatus}</p>
          <p><strong>Diet preferences:</strong>
            <#if consumer.preferredTags?has_content><#list consumer.preferredTags as t>${t}<#sep>, </#list><#else>None</#if>
          </p>
          <p><strong>Avoiding:</strong>
            <#if consumer.blacklistedTags?has_content><#list consumer.blacklistedTags as t>${t}<#sep>, </#list><#else>None</#if>
          </p>

          <a href="/ui/consumers/profile/edit" class="btn btn-primary">Edit Profile</a>

          <form method="post" action="/ui/consumers/profile/delete" class="d-inline"
                onsubmit="return confirm('Delete your account? This cannot be undone.');">
            <button type="submit" class="btn btn-outline-danger">Delete Account</button>
          </form>
        </div>
      </div>
    </section>

  </div>
</body>

</html>
