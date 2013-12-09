### Repository of the Java/Android training.

**Folders organization:**

Android folder: Contains Android projects.

Java folder: Contains console based Java projects.

**How to import existing projects into Eclipse (in pt-pt):**

Java projects: 

Para importar o projecto, como não tem ficheiros metadados a indicar que é um projecto, é necessário incluir de uma outra forma.
Primeiro, coloca-se a pasta do projecto numa directoria conhecida, como por exemplo, dentro da pasta workspace do Eclipse. Em segundo lugar tem-se que adicionar o projecto. Como o projecto não tem metadados é necessário criar no Eclipse, ou seja, em vez de ser com a Opção Import, cria-se mesmo com a opção new Java Project (a mesma opção que se utiliza para criar novos projectos em Java). Quando aparece o wizard do New Java Project, coloca-se o nome do projecto e depois em vez de utilizar a “Use default location”, desmarca-se essa opção e na Location faz-se browse para a pasta do projecto. Desta forma o Eclipse associa os folders src e lib e consegue criar o projecto.

Android projects:

Dentro do workspace, fazer Import->Android->Existing Code Into Workspace, e por fim escolher a directoria do projecto.