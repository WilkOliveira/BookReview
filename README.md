# BookReview

Projeto envolvendo mineração de texto, o programa recebe como entrada um arquivo em formato .csv .txt, ou similar, pre-processado agrupando os comentários em linha.

O programa usa uma serie de palavras-chave e as relaciona com três diferentes tipos de avaliação/classe (bom, médio ou ruim).

Cada linha do arquivo (avaliação escrita) é percorrida palavra por palavra, adicionando cada palavra-chave a sua respectiva classe.

Ao final, o programa mostra para cada usuário a frequência de cada classe e uma indicação da classificação do livro de acordo com cada comentário.

Caso deseje, funcionalidades como imprimir cada comentário separadamente e ver a quantidade total de palavras podem ser ativadas, para isto, basta habilitar alguns trechos de código que estão comentados (desativadas) a partir da linha 128.

Próximas funcionalidades:
•	Permitir ao usuário inserir o arquivo manualmente;
•	Adicionar novas palavras ao dicionário de cada categoria;
•	Mostrar a média geral de frequência de cada classe.
