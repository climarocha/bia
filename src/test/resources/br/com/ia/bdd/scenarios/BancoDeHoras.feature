Feature: Hours Bank Rules

  @important
  Scenario: Um funcionario possui a configuracao padrao de horario, realiza as batidas dentro do seu horario de trabalho e cumpre exatamente jornada de trabalho horas 
    Given Um funcionario for trabalhar e entrar as "08:25", sair para o almoco as "12:15", retornar do almoco as "13:25" e sair as "17:35"
    When em um dia util da semana 
    Then O funcionario trabalhou "08:00" horas, tem saldo de horas do banco de "00:00", tem saldo de horas de atraso do banco de "00:00", tem "00:00" horas extras e atrasou: "00:00"
  
  @important
  Scenario: Um funcionario possui a configuracao padrao de horario, realiza as batidas dentro do seu horario de trabalho e realiza 0,5h a mais da sua jornada de trabalho
    Given Um funcionario for trabalhar e entrar as "08:25", sair para o almoco as "12:25", retornar do almoco as "13:25" e sair as "17:55"
    When em um dia util da semana
    Then O funcionario trabalhou "08:30" horas, tem saldo de horas do banco de "00:30", tem saldo de horas de atraso do banco de "00:00", tem "00:00" horas extras e atrasou: "00:00"
    
  Scenario: Um funcionario possui a configuracao padrao de horario, realiza as batidas dentro do seu horario e atrasa "00:10" minutos no almoco
    Given Um funcionario for trabalhar e entrar as "09:00", sair para o almoco as "12:30", retornar do almoco as "13:40" e sair as "18:00"
    When em um dia util da semana
    Then O funcionario trabalhou "07:50" horas, tem saldo de horas do banco de "00:00", tem saldo de horas de atraso do banco de "00:00", tem "00:00" horas extras e atrasou: "00:10"
    
 Scenario: Um funcionario possui a configuracao padrao de horario, realiza horas extras tanto para pagamento quanto para banco de horas
    Given Um funcionario for trabalhar e entrar as "07:00", sair para o almoco as "12:00", retornar do almoco as "14:00" e sair as "21:00"
    When em um dia util da semana
    Then O funcionario trabalhou "12:00" horas, tem saldo de horas do banco de "01:00", tem saldo de horas de atraso do banco de "00:00", tem "03:00" horas extras e atrasou: "00:30"
    
  Scenario: Um funcionario possui a configuracao padrao de horario, realiza as batidas dentro do seu horario, mas fica devendo 0,5h no seu banco
    Given Um funcionario for trabalhar e entrar as "08:00", sair para o almoco as "12:00", retornar do almoco as "13:30" e sair as "17:00"
    When em um dia util da semana
    Then O funcionario trabalhou "07:30" horas, tem saldo de horas do banco de "00:00", tem saldo de horas de atraso do banco de "00:30", tem "00:00" horas extras e atrasou: "00:00"
   
  Scenario: Um funcionario possui a configuracao padrao de horario, mas suas com alguns atrasos de banco e atrasos que serão descontados 
    Given Um funcionario for trabalhar e entrar as "09:30", sair para o almoco as "12:00", retornar do almoco as "13:40" e sair as "17:20"
    When em um dia util da semana
    Then O funcionario trabalhou "06:10" horas, tem saldo de horas do banco de "00:00", tem saldo de horas de atraso do banco de "01:10", tem "00:00" horas extras e atrasou: "00:40"
    
    
#  Scenario: Um funcionario possui a configuracao padrao de horario e trabalhou apenas um período, mesmo assim será tirado o horário do seu almoço
#    Given Um funcionario for trabalhar e entrar as "08:00" e sair as "14:00"
#    When em um dia util da semana
#    Then O funcionario trabalhou "05:00" horas, tem saldo de horas do banco de "00:00", tem saldo de horas de atraso do banco de "03:00", tem "00:00" horas extras e atrasou: "00:00"