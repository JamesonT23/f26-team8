<!DOCTYPE html>
<html>
<head>
    <title>My Meal Catalog</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
          rel="stylesheet">
</head>
<body class="container mt-4">

    <h1>My Meal Catalog</h1>

    <a href="/provider/${chef.id}/meals/new" class="btn btn-primary mb-3">
        Add New Meal
    </a>

    <#if meals?has_content>
        <div class="row">
            <#list meals as meal>
                <div class="col-md-4 mb-3">
                    <div class="card p-3">
                        <h5>${meal.name}</h5>
                        <p>${meal.description!"No description available"}</p>

                        <a href="/meals/${meal.id}" class="btn btn-outline-primary">
                            View Nutrition Details
                        </a>
                    </div>
                </div>
            </#list>
        </div>
    <#else>
        <p>You haven't added any meals yet.</p>
    </#if>

</body>
</html>