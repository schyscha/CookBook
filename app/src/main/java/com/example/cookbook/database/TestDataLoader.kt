package com.example.cookbook.database

import android.content.Context

class TestDataLoader(val context: Context, val database: CookBookDatabase) : Runnable {

    /*
    Źródłem danych jest serwis pysznosci.pl
     */
    override fun run() {
        val tagDao = database.tagDao()
        val recipeDAO = database.recipeDao()
        val ingredientDAO = database.ingredientDao()
        val recipeIngredientDAO = database.recipeIngredientDao()
        val recipeTagDAO = database.recipeTagDao()

        // https://pysznosci.pl/przepisy/kurczak-z-piekarnika/
        tagDao.insertAll(
            Tag(0, "kolacje"),
            Tag(0, "kurczak")
        )

        ingredientDAO.insertAll(
            Ingredient(0, "kurczak", false),
            Ingredient(0, "oliwa z oliwek", false),
            Ingredient(0, "papryczki zielone", false),
            Ingredient(0, "ząbki czosnku", false),
            Ingredient(0, "cebula", false),
            Ingredient(0, "sól", true)
        )

        recipeDAO.insert(Recipe(0,
            "Marynowany kurczak z piekarnika",
            arrayListOf("https://pysznosci.pl/wp-content/uploads/2019/04/kurczak-z-piekarnika5.jpg"),
            "Cały proces rozpocznij od usunięcia kręgosłupa z kurczaka. Następnie dociśnij kurczaka do blatu, aby spłaszczyć mięso.\n" +
                    "Teraz przygotuj marynatę w blenderze z: oliwę z oliwek, zalewę z marynowanej papryki, sok z cytryny, marynowaną paprykę, zielone papryczki, czosnek, suszone oregano, sól, pieprz, wędzoną paprykę, cukier oraz słodką paprykę.\n" +
                    "3/4 marynaty rozprowadź na kurczaku. Odstaw na 2 godziny do lodówki, po czym piecz w piekarniku przez godzinę (temp. 200 stopni Celsjusza).\n" +
                    "Resztę marynaty zagotuj i połącz z majonezem. I sos jest już gotowy!",
            5f
            ))

        recipeIngredientDAO.insertAll(
            RecipeIngredient(1, 1, 1, "szt."),
            RecipeIngredient(1, 2, 150, "ml"),
            RecipeIngredient(1, 3, 3, "szt."),
            RecipeIngredient(1, 4, 6, "szt."),
            RecipeIngredient(1, 5, 1, "szt."),
            RecipeIngredient(1, 6, 1, "łyżeczka")
        )

        recipeTagDAO.insertAll(
            RecipeTag(1, 1),
            RecipeTag(1, 2)
        )

        // https://pysznosci.pl/przepisy/paszteciki-drozdzowe/
        tagDao.insert(Tag(0, "przekąski"))

        ingredientDAO.insertAll(
            Ingredient(0, "mąka pszenna", false),
            Ingredient(0, "drożdże", false),
            Ingredient(0, "pieczarki", false)
        )

        recipeDAO.insert(
            Recipe(0,
                "Paszteciki drożdżowe",
                arrayListOf(
                    "https://pysznosci.pl/wp-content/uploads/2019/04/paszteciki-drozdzowe4.jpg",
                    "https://pysznosci.pl/wp-content/uploads/2019/04/paszteciki-drozdzowe6.jpg",
                    "https://pysznosci.pl/wp-content/uploads/2019/04/paszteciki-drozdzowe10.jpg"
                ),
                "Na patelni podsmaż ze sobą: cebulę, pieczarki, paprykę słodką, sól oraz pieprz. Dokładnie wymieszaj.\n" +
                    "Umieść farsz na cieście drożdżowym i zwiń w ruloniki. Jak wcześniej podziel na mniejsze kawałki i użyj drewnianej łyżki.\n" +
                    "Wstaw do piekarnika na 25-30 minut (temp. 180 stopni Celsjusza).",
                5f
            )
        )

        recipeIngredientDAO.insertAll(
            RecipeIngredient(2, 7, 500, "g"),
            RecipeIngredient(2, 8, 45, "g"),
            RecipeIngredient(2, 9, 300, "g"),
            RecipeIngredient(2, 5, 1, "szt."),
            RecipeIngredient(2, 6, 1, "łyżeczka")
        )

        recipeTagDAO.insert(RecipeTag(2, 3))

        // https://pysznosci.pl/przepisy/kolorowe-roladki-schabowe/
        tagDao.insert(Tag(0, "dania"))

        ingredientDAO.insertAll(
            Ingredient(0, "schab wieprzowy", false),
            Ingredient(0, "ser żółty", false),
            Ingredient(0, "bułka tarta", true)
        )

        recipeDAO.insert(
            Recipe(0,
                "Roladki schabowe",
                arrayListOf("https://pysznosci.pl/wp-content/uploads/2019/04/kolorowe-roladki-schabowe5.jpg"),
                "Odetnij końcówki fasolek szparagowych, po czym podgotuj przez kilka minut.\n" +
                        "Roztłucz przy pomocy tłuczka kawałki schabu. Następnie natrzyj je czosnkiem i dopraw przy pomocy soli i pieprzu. Na środku ułóż fasolkę, ser i paprykę. Zwiń w roladkę.\n" +
                        "Obtocz w jajkach i bułce tartej. Smaż przez kilka minut na patelni, po czym wstaw do piekarnika na 15 minut (temp. 160 stopni Celsjusza).\n" +
                        "Roladki schabowe podawaj z ulubionymi dodatkami. My zdecydowaliśmy się na frytki.",
                3.5f
            )
        )

        recipeIngredientDAO.insertAll(
            RecipeIngredient(3, 10, 5, "szt."),
            RecipeIngredient(3, 11, 100, "g"),
            RecipeIngredient(3, 12, 1, "szkl."),
            RecipeIngredient(3, 4, 3, "szt."),
            RecipeIngredient(3, 6, 1, "łyżeczka")
        )

        recipeTagDAO.insertAll(
            RecipeTag(3, 4),
            RecipeTag(3, 1)
        )
    }
}