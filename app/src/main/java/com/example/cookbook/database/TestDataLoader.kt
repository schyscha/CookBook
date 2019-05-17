package com.example.cookbook.database


class TestDataLoader(val database: CookBookDatabase) : Runnable {

    /*
    Źródłem danych jest serwis pysznosci.pl
     */
    override fun run() {
        database.insert(
            "Marynowany kurczak z piekarnika",
            arrayListOf("https://pysznosci.pl/wp-content/uploads/2019/04/kurczak-z-piekarnika5.jpg"),
            "Cały proces rozpocznij od usunięcia kręgosłupa z kurczaka. Następnie dociśnij kurczaka do blatu, aby spłaszczyć mięso.\n" +
                    "Teraz przygotuj marynatę w blenderze z: oliwę z oliwek, zalewę z marynowanej papryki, sok z cytryny, marynowaną paprykę, zielone papryczki, czosnek, suszone oregano, sól, pieprz, wędzoną paprykę, cukier oraz słodką paprykę.\n" +
                    "3/4 marynaty rozprowadź na kurczaku. Odstaw na 2 godziny do lodówki, po czym piecz w piekarniku przez godzinę (temp. 200 stopni Celsjusza).\n" +
                    "Resztę marynaty zagotuj i połącz z majonezem. I sos jest już gotowy!",
            5f,
            arrayListOf(
                CompleteRecipeIngredientRelation(0, "kurczak", 1, "szt.", false),
                CompleteRecipeIngredientRelation(0, "oliwa z oliwek", 150, "ml", false),
                CompleteRecipeIngredientRelation(0, "papryczki zielone", 3, "szt.", false),
                CompleteRecipeIngredientRelation(0, "ząbki czosnku", 6, "szt.", false),
                CompleteRecipeIngredientRelation(0, "cebula", 1, "szt.", false),
                CompleteRecipeIngredientRelation(0, "sól", 1, "łyżeczka", true)
            ),
            arrayListOf("kolacje", "kurczak")
        )

        database.insert(
            "Paszteciki drożdżowe",
            arrayListOf(
                "https://pysznosci.pl/wp-content/uploads/2019/04/paszteciki-drozdzowe4.jpg",
                "https://pysznosci.pl/wp-content/uploads/2019/04/paszteciki-drozdzowe6.jpg",
                "https://pysznosci.pl/wp-content/uploads/2019/04/paszteciki-drozdzowe10.jpg"
            ),
            "Na patelni podsmaż ze sobą: cebulę, pieczarki, paprykę słodką, sól oraz pieprz. Dokładnie wymieszaj.\n" +
                    "Umieść farsz na cieście drożdżowym i zwiń w ruloniki. Jak wcześniej podziel na mniejsze kawałki i użyj drewnianej łyżki.\n" +
                    "Wstaw do piekarnika na 25-30 minut (temp. 180 stopni Celsjusza).",
            5f,
            arrayListOf(
                CompleteRecipeIngredientRelation(0, "mąka pszenna", 500, "g", false),
                CompleteRecipeIngredientRelation(0, "drożdże", 45, "g", false),
                CompleteRecipeIngredientRelation(0, "pieczarki", 300, "g", false),
                CompleteRecipeIngredientRelation(0, "cebula", 1, "szt.", false),
                CompleteRecipeIngredientRelation(0, "sól", 1, "łyżeczka", true)
            ),
            arrayListOf("przekąski")
        )

        database.insert(
            "Roladki schabowe",
            arrayListOf("https://pysznosci.pl/wp-content/uploads/2019/04/kolorowe-roladki-schabowe5.jpg"),
            "Odetnij końcówki fasolek szparagowych, po czym podgotuj przez kilka minut.\n" +
                    "Roztłucz przy pomocy tłuczka kawałki schabu. Następnie natrzyj je czosnkiem i dopraw przy pomocy soli i pieprzu. Na środku ułóż fasolkę, ser i paprykę. Zwiń w roladkę.\n" +
                    "Obtocz w jajkach i bułce tartej. Smaż przez kilka minut na patelni, po czym wstaw do piekarnika na 15 minut (temp. 160 stopni Celsjusza).\n" +
                    "Roladki schabowe podawaj z ulubionymi dodatkami. My zdecydowaliśmy się na frytki.",
            3.5f,
            arrayListOf(
                CompleteRecipeIngredientRelation(0, "schab wieprzowy", 5, "szt.", false),
                CompleteRecipeIngredientRelation(0, "ser żółty", 100, "g", false),
                CompleteRecipeIngredientRelation(0, "bułka tarta", 1, "szkl.", true),
                CompleteRecipeIngredientRelation(0, "ząbki czosnku", 3, "szt.", false),
                CompleteRecipeIngredientRelation(0, "sól", 1, "łyżeczka", true)
            ),
            arrayListOf("kolacje", "dania")
        )
    }
}