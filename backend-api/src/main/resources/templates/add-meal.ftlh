<!DOCTYPE html>
<html>
<head>
    <title>Add a Meal</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
          rel="stylesheet">
</head>
<body class="container mt-4">

    <h1>Add a New Meal</h1>

    <form id="addMealForm">

        <div class="mb-3">
            <label class="form-label">Meal Name</label>
            <input type="text" id="name" class="form-control" required>
        </div>

        <div class="mb-3">
            <label class="form-label">Description</label>
            <textarea id="description" class="form-control" required></textarea>
        </div>

        <div class="mb-3">
            <label class="form-label">Calories</label>
            <input type="number" id="calories" class="form-control"
                   min="0" required>
        </div>

        <div class="mb-3">
            <label class="form-label">Carbohydrates</label>
            <input type="number" id="carbohydrates" class="form-control"
                   min="0" required>
        </div>

        <div class="mb-3">
            <label class="form-label">Protein</label>
            <input type="number" id="protein" class="form-control"
                   min="0" required>
        </div>

        <div class="mb-3">
            <label class="form-label">Fat</label>
            <input type="number" id="fat" class="form-control"
                   min="0" required>
        </div>

        <button type="submit" class="btn btn-primary">Add Meal</button>

        <a href="/provider/${chef.id}/meals"
           class="btn btn-secondary">Cancel</a>
    </form>

    <p id="message" class="mt-3"></p>

    <script>
        document.getElementById("addMealForm").addEventListener("submit",
            async function(event) {
                event.preventDefault();

                const meal = {
                    name: document.getElementById("name").value,
                    description: document.getElementById("description").value,
                    calories: Number(document.getElementById("calories").value),
                    carbohydrates: Number(document.getElementById("carbohydrates").value),
                    protein: Number(document.getElementById("protein").value),
                    fat: Number(document.getElementById("fat").value),
                    dietTags: [],
                    blacklistTags: []
                };

                try {
                    const response = await fetch("/api/meals/chef/${chef.id}", {
                        method: "POST",
                        headers: {
                            "Content-Type": "application/json"
                        },
                        body: JSON.stringify(meal)
                    });

                    if (response.ok) {
                        window.location.href = "/provider/${chef.id}/meals";
                    } else {
                        document.getElementById("message").textContent =
                            "Unable to add meal. Please check your information.";
                    }
                } catch (error) {
                    document.getElementById("message").textContent =
                        "An error occurred while adding the meal.";
                }
            });
    </script>

</body>
</html>
