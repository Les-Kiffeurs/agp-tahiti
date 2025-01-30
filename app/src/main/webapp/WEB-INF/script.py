import os

# Chemin du dossier contenant les fichiers
dossier = "descriptions"

# Récupérer tous les fichiers .txt et les trier
fichiers = sorted([f for f in os.listdir(dossier) if f.endswith(".txt")])

# Renommer chaque fichier en 1.txt, 2.txt, etc.
for i, fichier in enumerate(fichiers, start=1):
    ancien_chemin = os.path.join(dossier, fichier)
    nouveau_chemin = os.path.join(dossier, f"{i}.txt")
    os.rename(ancien_chemin, nouveau_chemin)

print("Renommage terminé !")