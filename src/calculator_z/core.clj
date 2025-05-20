(ns calculator-z.core
  (:gen-class))

;;(def chave "")
;; gravar as transações no atom
;; usar o swap!

(def api-url "https://caloriasporalimentoapi.herokuapp.com/")

(http-client/get api-url
  {:query-params {"q" "calorias"
                  "api-Key" chave}})



(defn ganho []
  (println "Chamando funcao ganho"))

(defn perda []
  (println "Chamando funcao perda"))



(defn -main []
  (println "\nMenu")
  (println "Opcao 1 - Ganho")
  (println "Opcao 2 - Perda")
  (println "Opcao 3 - Sair")
  (def op (read))

  (cond
    (= op 1) (do (ganho) (recur))
    (= op 2) (do (perda) (recur))
    (= op 3) (println "Saindo...")))

(-main)