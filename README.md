# Ajla Zilkikj 233192
# Control Flow Graph
![CFG searchBookByTitle](cfg_searchBookByTitle.png)
![CFG borrowBook](cfg_borrowBook.png)
# Цикломатска комплексност
searchBookByTitle
Предикатни јазли (јазли со повеќе гранки): 1, 4, 5, 7 → P = 4
Цикломатска комплексност = P + 1 = 5
Алтернативно по формулата M = E - N + 2P:

E (ребра) = 10, N (јазли) = 9, P (поврзани компоненти) = 1
M = 10 - 9 + 2 = 3 (минимална, без јамка)

Со јамката: постојат 4 предикатни јазли → цикломатска комплексност = 4.
borrowBook
Предикатни јазли: 1, 3, 4, 5 → P = 4
Цикломатска комплексност = P + 1 = 5
## Тест случаи според критериумот Every Statement

### searchBookByTitle

| | test 1 | test 2 | test 3 |
|---|---|---|---|
| line 1 (`if title.isEmpty()` → throw) | * | * | * |
| line 2 (`results = new ArrayList()`) | | * | * |
| line 3 (`for each book`) | | * | * |
| line 4 (`if title matches && !borrowed` → `results.add`) | | * | |
| line 5 (`if results.isEmpty()` → return null) | | | * |
| line 6 (`return results`) | | * | |

Минималниот број на тест случаи за оваа функција според Every Statement критериумот е 3.

**Опис на тест случаите:**

- **test 1** — title е празен (`""`) → фрла `IllegalArgumentException`
  - Покрива: line 1
- **test 2** — title постои и книгата не е изнајмена → враќа листа со книга
  - Покрива: line 1, line 2, line 3, line 4, line 6
- **test 3** — title не постои во библиотеката → враќа `null`
  - Покрива: line 1, line 2, line 3, line 5

---

## Тест случаи според критериумот Every Branch

### borrowBook

| | test 1 | test 2 | test 3 | test 4 |
|---|---|---|---|---|
| branch 1 — `title\|\|author` isEmpty → throw (`true`) | * | | | |
| branch 2 — `title\|\|author` isEmpty → продолжи (`false`) | | * | * | * |
| branch 3 — title && author match → влези (`true`) | | | * | * |
| branch 4 — title && author не совпаѓа → следна книга (`false`) | | * | | |
| branch 5 — `!borrowed` → setBorrowed (`true`) | | | * | |
| branch 6 — `borrowed` → throw already borrowed (`false`) | | | | * |
| branch 7 — book not found → throw | | * | | |

Минималниот број на тест случаи за оваа функција според Every Branch критериумот е 4.

**Опис на тест случаите:**

- **test 1** — author е празен (`""`) → фрла `IllegalArgumentException`
  - Покрива: branch 1
- **test 2** — книгата не постои во библиотеката → фрла `RuntimeException("Book not found")`
  - Покрива: branch 2, branch 4, branch 7
- **test 3** — книгата постои и не е изнајмена → успешно изнајмување
  - Покрива: branch 2, branch 3, branch 5
- **test 4** — книгата постои но веќе е изнајмена → фрла `RuntimeException("Book is already borrowed")`
  - Покрива: branch 2, branch 3, branch 6

---

## Тест случаи според критериумот Multiple Condition

### searchBookByTitle — услов: `book.getTitle().equalsIgnoreCase(title) && !book.isBorrowed()`

| ime na funkcija | test |
|---|---|
| TT (title совпаѓа, не е изнајмена) | test za TT |
| TF (title совпаѓа, е изнајмена) | test za TF |
| FT (title не совпаѓа, не е изнајмена) | test za FT |

Минималниот број на тест случаи за оваа функција според Multiple Condition критериумот е 3.

**Опис на тест случаите:**

- **TT** — `"Clean Code"` постои и не е изнајмена → враќа листа со книга
- **TF** — `"1984"` е изнајмена → не се додава, враќа `null`
- **FT** — `"Harry Potter"` не постои → враќа `null`

*(FF не е потребно бидејќи `&&` се евалуира лево-надесно — ако A е false, B не се проверува)*

### borrowBook — услов: `title.isEmpty() || author.isEmpty()`

| ime na funkcija | test |
|---|---|
| TT (и title и author се празни) | test za TT |
| TF (само title е празен) | test za TF |
| FT (само author е празен) | test za FT |
| FF (и двете непразни) | test za FF |

Минималниот број на тест случаи за оваа функција според Multiple Condition критериумот е 4.

**Опис на тест случаите:**

- **TT** — `borrowBook("", "")` → фрла `IllegalArgumentException`
- **TF** — `borrowBook("", "J.R.R. Tolkien")` → фрла `IllegalArgumentException`
- **FT** — `borrowBook("The Hobbit", "")` → фрла `IllegalArgumentException`
- **FF** — `borrowBook("Clean Code", "Robert C. Martin")` → нема исклучок, продолжува
