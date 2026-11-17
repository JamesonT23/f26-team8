<!DOCTYPE html>
<html>
<head>
    <title>Meal Details</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
          rel="stylesheet">
</head>
<body class="container mt-4">

    <h1>${meal.name}</h1>

    <p>${meal.description!"No description available"}</p>

    <h3>Nutritional Information</h3>

    <ul class="list-group mb-3">
        <li class="list-group-item">
            Calories: ${meal.calories}
        </li>
        <li class="list-group-item">
            Carbohydrates: ${meal.carbohydrates}
        </li>
        <li class="list-group-item">
            Protein: ${meal.protein}
        </li>
        <li class="list-group-item">
            Fat: ${meal.fat}
        </li>
    </ul>

    <a href="/provider/${meal.chef.id}/meals"
       class="btn btn-secondary">Back to Meal Catalog</a>

</body>
</html>
